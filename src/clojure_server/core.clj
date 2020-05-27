(ns clojure-server.core)
(use 'org.httpkit.server)

(defn counter []
  (let [tick (atom 0)]
    #(swap! tick inc)))

(def tick (counter))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (takeg (repeatedly tick))})


(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  (reset! server (run-server #'app {:port 8080})))

(defn return-number []
  #(+ 10 5))

(def help-me (return-number))

(help-me)

(take 10 (repeatedly (return-number)))
