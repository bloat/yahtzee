(ns yahzee.roll-test
  (:use clojure.test
        yahzee.roll))

(deftest test-one-dice
  (is (= #{1 2 3 4 5 6} (set (repeatedly 10000 roll)))))

(deftest test-five-dice
  (= 5 (count (five-dice))))

(deftest test-keep-and-roll
  (is (= #{1 2 3 4 5} (set (keep-and-roll [1 2 3 4 5] 0 1 2 3 4))))
  (is (some (partial = 5) (keep-and-roll [5 1 1 1 1] 0)))
  (is (let [new-roll (keep-and-roll [1 2 3 4 5] 3 1)]
        (and (some (partial = 2) new-roll)
             (some (partial = 4) new-roll)))))

