(ns corpus-dev-container-clojure-kit.web.routes.api
  (:require
   [corpus-dev-container-clojure-kit.web.controllers.home :as home]
   [integrant.core :as ig]))

(defn routes
  [_opts]
  [["/" {:get #'home/index}]
   ["/healthz" {:get #'home/healthz}]])

(derive :reitit.routes/api :reitit/routes)

(defmethod ig/init-key :reitit.routes/api
  [_ {:keys [base-path] :or {base-path ""} :as opts}]
  (fn [] (into [base-path] (routes opts))))
