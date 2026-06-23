(ns corpus-dev-container-clojure-kit.web.handler
  (:require
   [integrant.core :as ig]
   [muuntaja.core :as m]
   [reitit.ring :as ring]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [ring.middleware.defaults :as defaults]
   [ring.middleware.session.cookie :as cookie]
   [ring.util.response :as response]))

(defn wrap-base
  [{:keys [site-defaults-config cookie-secret]}]
  (let [cookie-store (cookie/cookie-store {:key (.getBytes ^String cookie-secret)})]
    (fn [handler]
      (defaults/wrap-defaults
       handler
       (assoc-in site-defaults-config [:session :store] cookie-store)))))

(defmethod ig/init-key :handler/ring
  [_ {:keys [router] :as opts}]
  (ring/ring-handler
   (router)
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-resource-handler {:path "/"})
    (ring/create-default-handler
     {:not-found
      (constantly (-> {:status 404 :body "Page not found"}
                      (response/content-type "text/plain")))}))
   {:middleware [(wrap-base opts)]}))

(defmethod ig/init-key :router/routes
  [_ {:keys [routes]}]
  (mapv (fn [route]
          (if (fn? route) (route) route))
        routes))

(defmethod ig/init-key :router/core
  [_ {:keys [routes] :as opts}]
  (constantly
   (ring/router
    ["" opts routes]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})))
