pso
===

A simple Particle Swarm Optimizer

Inspired by the Java PSO implementation in http://github.com/fearofcode/bateman

For more on Particle Swarm Optimization, check out this Wikipedia article: http://en.wikipedia.org/wiki/Particle_swarm_optimization


Usage
===

Building a particle swarm requires but four parameters:

1) An ordered collection of dimensions, each with a label, a maximum value, and a minimum value.

2) A fitness function that takes as many arguments as there are dimensions.

3) A java.util.Comparator function that orders pairs of fitness function results.

4) The number of particles in the swarm.


```clojure
(require 'pso.swarm 'pso.dimension)

(def dimensions [(pso.dimension/build :numerator 0 100)
                 (pso.dimension/build :denominator 1 100)])

(loop [swarm (pso.swarm/build dimensions / (comparator >) 30)
       generations 100]
  (if (zero? generations)
    (get-in swarm [:best-particle :result :position])
    (recur (pso.swarm/update swarm) (dec generations))))
```
