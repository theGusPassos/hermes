# Hermes - Turn Based RPG for Discord

# Developers

## About this code
 - **parenthesin**: Helpers and wrappers to give a foundation to create new services in clojure,
you can find components for database, http, webserver and tools for db migrations.
 - **hermes**: The bot.

## Available Endpoints

Verb | URL                           | Description
-----| ----------------------------- | ------------------------------------------------
POST | /discord/webhook              | Receives Discord's [interaction object](https://discord.com/developers/docs/interactions/slash-commands#interaction-object)

## Configure the config.edn 
Set the [`resources/config.edn`](resources/config.edn) with your keys or the corresponding enviroment variables.  

### Repl
You can start a repl open and evaluate the file `src/super_dice_roll/server.clj` and execute following code:
```clojure
(start-system! (build-system-map))
```

### Uberjar
You can generate an uberjar and execute it via java in the terminal:
```bash
# genarate a service.jar in the root of this repository.
clj -X:uberjar
# execute it via java
java -jar service.jar
```

## Repl
To open a nrepl
```bash
clj -M:nrepl
```
To open a nrepl with all test extra-deps on it
```bash
clj -M:test:nrepl
```

## Run Tests
To run unit tests inside `./test/unit`
```bash
clj -M:test :unit
```
To run integration tests inside `./test/integration`
```bash
clj -M:test :integration
```
To run all tests inside `./test`
```bash
clj -M:test
```
To generate a coverage report 
```bash
clj -M:test --plugin kaocha.plugin/cloverage
```

## Lint
Runs LSP to fix format and namespaces
```bash
clojure -M:clojure-lsp format
clojure -M:clojure-lsp clean-ns
```
Runs LSP to check format and namespaces
```bash
clojure -M:clojure-lsp format --dry
clojure -M:clojure-lsp clean-ns --dry
clojure -M:clojure-lsp diagnostics
```

## Directory Structure
```
./
├── .clj-kondo -- clj-kondo configuration and classes
├── .github
│   └── workflows -- Github workflows folder.
├── resources -- Application resources assets folder and configuration files
├── src -- Library source code and headers.
│   ├── parenthesin -- Source for common utilities and helpers.
│   └── hermes -- Source for the bot.
└── test -- Test source code.
    ├── integration -- Integration tests source (uses state-flow).
    │   ├── parenthesin -- Tests for common utilities and helpers.
    │   └── hermes -- Tests for bot.
    └── unit -- Unity tests source (uses clojure.test).
        ├── parenthesin -- Tests for common utilities.
        └── hermes -- Tests for bot.
```

## Based on
- https://github.com/rafaeldelboni/super-dice-roll
- https://github.com/rafaeldelboni/node-super-dice-roll
