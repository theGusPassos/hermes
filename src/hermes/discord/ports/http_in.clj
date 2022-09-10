(ns hermes.discord.ports.http-in
  (:require [schema.core :as s]
            [hermes.discord.schemas.http-in :as discord.schemas.http-in]
            [hermes.schemas.types :as schemas.types]))

(s/defn application-command-handler!
  [body :- discord.schemas.http-in/InteractionRequest
   components :- schemas.types/Components]
  (let [slash-cmd (get-in body [:data :name])
        content (case slash-cmd
                  "test" "test successfull =)"
                  "something went wrong")]
    {:status 200
     :body {:type 4
            :data {:content content}}}))

(defn process-interaction!
  [{{{:keys [type] :as body} :body} :parameters
    components :components}]
  (case type
    1 {:status 200
       :body {:type 1}}
    2 (application-command-handler! body components)
    {:status 200
     :body {:type 4
            :data {:content "Unknown command. "}}}))
