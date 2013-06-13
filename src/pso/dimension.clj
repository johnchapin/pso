(ns pso.dimension)

(defn bounded-rand [{:keys [minimum width]}]
  (+ minimum (rand width)))

(defn build [tag minimum maximum]
  {:tag tag
   :minimum minimum
   :maximum maximum
   :width (- maximum minimum)})
