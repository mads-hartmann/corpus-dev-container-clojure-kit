(ns corpus-dev-container-clojure-kit.web.controllers.home
  (:require
   [ring.util.http-response :as http-response]))

(defn index
  [_request]
  (-> (http-response/ok "<!DOCTYPE html>\n<html><head><title>Clojure Kit</title></head>\n<body><h1>Hello from Clojure Kit</h1></body></html>")
      (http-response/content-type "text/html")))

(defn healthz
  [_request]
  (http-response/ok {:status "ok" :framework "kit"}))
