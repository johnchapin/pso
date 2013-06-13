(ns pso.unit.dimension
  (:require [pso.dimension :refer :all]
            [midje.sweet :refer :all]))

(facts
  "build"
  (build :foo 0 100) => {:tag :foo :minimum 0 :maximum 100 :width 100}
  (build :bar -100 0) => {:tag :bar :minimum -100 :maximum 0 :width 100}
  (build :bar -50 50) => {:tag :bar :minimum -50 :maximum 50 :width 100})

(facts
  "bounded-rand"
  (bounded-rand {:minimum 0 :width 100}) => (roughly 50 50)
  (bounded-rand {:minimum -100 :width 100}) => (roughly -50 50)
  (bounded-rand {:minimum -50 :width 100}) => (roughly 0 50))
