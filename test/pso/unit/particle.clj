(ns pso.unit.particle
  (:require [pso.particle :refer :all]
            [midje.sweet :refer :all]))

(def dimensions [{:tag :foo :minimum -50 :maximum 50 :width 100}
                 {:tag :bar :minimum -10 :maximum 10 :width 2}])

(def swarm-skel {:dimensions dimensions
                 :fitness-fn *
                 :comparator-fn (comparator >)})

(facts
  "build"
  (build swarm-skel) => (contains
                          {:velocity (contains [(roughly 0 5) (roughly 0 1)])
                           :best-result (contains
                                          {:position (contains
                                                       [(roughly 0 50) (roughly 0 10)])})}))

(def particle-skel {:vel-mult 0.999
                    :pos-mult 1.5
                    :swarm-pos-mult 1.5})

(facts
  "transform"
  (transform (assoc particle-skel
                    :velocity [4 0.75]
                    :result {:position [0 0]}
                    :best-result {:position [0 0] :value 0})
             (assoc swarm-skel
                    :best-particle {:result {:position [0.5 0.5]}}))
  =>
  (contains
    {:best-result {:position [4 0.75], :value 3.0}
     :last-result {:position [0 0]}
     :result {:position [4 0.75] :value 3.0}
     :velocity (contains [(roughly 2.373 2.373) (roughly 0.749 0.749)])}))
