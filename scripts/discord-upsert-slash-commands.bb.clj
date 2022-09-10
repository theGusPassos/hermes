#!/usr/bin/env bb

(require '[babashka.curl :as curl]
         '[cheshire.core :as json]
         '[clojure.string :as string])

(def bot-token (or (System/getenv "DISCORD_BOT_TOKEN") ""))
(def application-id (or (System/getenv "DISCORD_APP_ID") ""))
(def commands-url (str "https://discord.com/api/v8/applications/" application-id "/commands"))

(def commands
  (json/encode
   [{:name "test"
     :description "Basic ping"
     :type 1}]))

(defn upsert-commands []
  (-> commands-url
      (curl/put {:headers {"Accept" "application/json"
                           "Content-Type" "application/json"
                           "Authorization" (str "Bot " bot-token)}
                 :body commands
                 :throw true
                 :compressed false})
      (dissoc :headers)
      println))

(defn list-current-commands []
  (-> commands-url
      (curl/get {:headers {"Accept" "application/json"
                           "Content-Type" "application/json"
                           "Authorization" (str "Bot " bot-token)} 
                 :compressed false
                 :throw false})
      :body
      (json/decode true)
      println))

(let [args *command-line-args*]
  (when (string/includes? args "--update")
    (upsert-commands))
  (when (string/includes? args "--list")
    (list-current-commands)))
