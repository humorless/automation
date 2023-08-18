# Install
1. `brew install clojure/tools/clojure`
2. `clj` 

# Config
1. fill in the file `dev/config.edn`
2. The most relevant fields in config file are:
  `:user`, `:password`, `:https`, `:host`, `:port`, `:db-name`
3. When `:host` field is domain name without port information, we should fill in `nil` in `:port` field.

# Execute the trigger

```
clj -X automation.auto/run 
```
