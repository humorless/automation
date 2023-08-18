# Install
1. `brew install clojure/tools/clojure`
2. `clj` 

# Config
1. fill in the file `dev/config.edn`

Note: 
1. if using https, port needs to be set as `nil`
2. The most relevant fields are: `:user`, `:password`, `:https`, `:host`, `:port`, `:db-name` 

# Execute the trigger

```
clj -X automation.auto/run 
```
