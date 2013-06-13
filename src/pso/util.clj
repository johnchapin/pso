(ns pso.util)

(defn v+
  "Add a collection of vectors"
  [& vs]
  {:pre [(apply = (map count vs))]}
  (apply mapv + vs))

(defn v-
  "Subtract a collection of vectors"
  [& vs]
  {:pre [(apply = (map count vs))]}
  (apply mapv - vs))

(defn nv*
  "Multiply a vector by a number"
  [n v]
  (map (partial * n) v))
