(ns pso.swarm
  (:require [pso.particle]))

(letfn [(value-fn [v] (get-in v [:result :value]))]

  (defn update [{:keys [particles comparator-fn] :as swarm}]
    (let [new-particles (pmap #(pso.particle/transform % swarm) particles)
          best-particle (first (sort-by value-fn comparator-fn particles))]
      (assoc swarm
             :particles new-particles
             :best-particle best-particle)))

  (defn build [dimensions fitness-fn comparator-fn size]
    (let [swarm {:fitness-fn fitness-fn
                 :comparator-fn comparator-fn
                 :dimensions dimensions}
          particles (repeatedly size #(pso.particle/build swarm))]
      (assoc swarm
             :particles particles
             :best-particle (first (sort-by value-fn comparator-fn particles))))))
