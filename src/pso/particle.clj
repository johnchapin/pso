(ns pso.particle
  (:require [pso
             [dimension]
             [util :refer :all]]))

(defn- calculate-velocity [particle best-particle
                           vel-mult pos-mult swarm-pos-mult]
  (let [v1 (nv* vel-mult (:velocity particle))
        v2 (nv* (rand pos-mult)
                (v- (get-in particle [:best-result :position])
                    (get-in particle [:result :position])))
        v3 (nv* (rand swarm-pos-mult)
                (v- (get-in best-particle [:result :position])
                    (get-in particle [:result :position])))]
    (v+ v1 v2 v3)))

(defn transform [{:keys [velocity result best-result vel-mult pos-mult swarm-pos-mult] :as particle}
                 {:keys [dimensions best-particle fitness-fn comparator-fn] :as swarm}]
  (let [new-position (->> (v+ (:position result) velocity)
                          (mapv min (map :maximum dimensions) ,,,)
                          (mapv max (map :minimum dimensions) ,,,))
        new-result {:position new-position :value (apply fitness-fn new-position)}]
    (assoc particle
           :velocity (calculate-velocity particle best-particle vel-mult pos-mult swarm-pos-mult)
           :result new-result
           :last-result result
           :best-result (first (sort-by :value comparator-fn [new-result best-result])))))

(defn build [{:keys [dimensions fitness-fn] :as swarm}
             & {:keys [vel-mult pos-mult swarm-pos-mult initial-vel-mult]
                :or {vel-mult 0.999 pos-mult 1.5 swarm-pos-mult 1.5 initial-vel-mult 0.1}}]

  (let [initial-position (map #(pso.dimension/bounded-rand %) dimensions)
        result {:position initial-position :value (apply fitness-fn initial-position)}]
    {:velocity (map #(* initial-vel-mult (pso.dimension/bounded-rand %)) dimensions)
     :result result
     :best-result result
     :vel-mult vel-mult
     :pos-mult pos-mult
     :swarm-pos-mult swarm-pos-mult}))
