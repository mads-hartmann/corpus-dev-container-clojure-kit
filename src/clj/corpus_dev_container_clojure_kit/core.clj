(ns corpus-dev-container-clojure-kit.core
  (:require
   [clojure.tools.logging :as log]
   [corpus-dev-container-clojure-kit.config :as config]
   [corpus-dev-container-clojure-kit.web.handler]
   [corpus-dev-container-clojure-kit.web.routes.api]
   [integrant.core :as ig]
   [kit.edge.server.undertow])
  (:gen-class))

(Thread/setDefaultUncaughtExceptionHandler
 (fn [thread ex]
   (log/error {:what :uncaught-exception
               :exception ex
               :where (str "Uncaught exception on " (.getName thread))})))

(defonce system (atom nil))

(defn stop-app []
  (some-> @system ig/halt!))

(defn start-app []
  (-> (config/system-config {})
      ig/expand
      ig/init
      (as-> initialized (reset! system initialized))))

(defn -main [& _]
  (start-app)
  (.addShutdownHook (Runtime/getRuntime) (Thread. #(do (stop-app) (shutdown-agents)))))
