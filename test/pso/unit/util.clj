(ns pso.unit.util
  (:require [pso.util :refer :all]
            [midje.sweet :refer :all]))

(facts
  "v+"
  (apply v+ [[1 2 3] [4 5 6]]) => [5 7 9]
  (apply v+ [[] []]) => []
  (apply v+ [[1 2 3] [4 5]]) => (throws java.lang.AssertionError))

(facts
  "v-"
  (apply v- [[1 2 3] [4 5 6]]) => [-3 -3 -3]
  (apply v- [[] []]) => []
  (apply v- [[1 2 3] [4 5]]) => (throws java.lang.AssertionError))

(facts
  "nv*"
  (nv* 0 [1 2 3]) => [0 0 0]
  (nv* 1 [1 2 3]) => [1 2 3]
  (nv* 2 [1 2 3]) => [2 4 6])
