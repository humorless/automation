# Requirements
To use Automation, we need to prepare Clojure CLI program in our system first.

`brew install clojure/tools/clojure`

# Config
1. fill in the file `dev/config.edn`
2. The most relevant fields in config file are:
  `:user`, `:password`, `:https`, `:host`, `:port`, `:db-name`
3. When `:host` field is domain name without port information, we should fill in `nil` in `:port` field.

# Execute the trigger

```
clj -X automation.auto/run 
```
