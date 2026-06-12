(ns corpus-dev-container-clojure-kit.web.controllers.home
  (:require
   [ring.util.http-response :as http-response]))

(defn index
  [_request]
  (http-response/ok "Hello from Clojure Kit"))

(defn healthz
  [_request]
  (http-response/ok {:status "ok" :framework "kit"}))
