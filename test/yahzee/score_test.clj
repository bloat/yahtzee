(ns yahzee.score-test
  (:use clojure.test
        yahzee.score))

(deftest test-sum-only-ones
  (is (= {:ones 5} (ones [1 1 1 1 1])))
  (is (= {:ones 3} (ones [1 1 1 3 3]))))

(deftest test-sum-only-twos
  (is (= {:twos 10} (twos [2 2 2 2 2])))
  (is (= {:twos 4} (twos [1 2 1 2 3]))))

(deftest test-sum-only-threes
  (is (= {:threes 15} (threes [3 3 3 3 3])))
  (is (= {:threes 0} (threes [1 2 4 5 6])))
  (is (= {:threes 6} (threes [1 2 3 3 6]))))

(deftest test-sum-only-fours
  (is (= {:fours 20} (fours [4 4 4 4 4])))
  (is (= {:fours 12} (fours [2 4 4 3 4]))))

(deftest test-sum-only-fours
  (is (= {:fours 20} (fours [4 4 4 4 4])))
  (is (= {:fours 12} (fours [2 4 4 3 4]))))

(deftest test-sum-only-fives
  (is (= {:fives 25} (fives [5 5 5 5 5])))
  (is (= {:fives 5} (fives [2 5 4 3 4]))))

(deftest test-sum-only-sixes
  (is (= {:sixes 30} (sixes [6 6 6 6 6])))
  (is (= {:sixes 18} (sixes [2 6 4 6 6]))))

(deftest test-sum-all-dice
  (is (= {:chance 15} (chance [1 2 3 5 4])))
  (is (= {:chance 6} (chance [1 1 1 1 2])))
  (is (= {:chance 28} (chance [6 6 6 5 5]))))

(deftest test-all-dice-equal
  (is (= {:yahzee 0} (yahzee [1 2 3 4 5])))
  (is (= {:yahzee 0} (yahzee [4 4 4 4 5])))
  (is (= {:yahzee 50} (yahzee [3 3 3 3 3])))
  (is (= {:yahzee 50} (yahzee [4 4 4 4 4]))))

(deftest test-three-of-a-kind
  (is (= {:three-of-a-kind 0} (three-of-a-kind [1 2 3 4 5])))
  (is (= {:three-of-a-kind 0} (three-of-a-kind [1 2 3 3 4])))
  (is (= {:three-of-a-kind 16} (three-of-a-kind [4 4 4 2 2])))
  (is (= {:three-of-a-kind 12} (three-of-a-kind [2 3 1 3 3])))
  (is (= {:three-of-a-kind 9} (three-of-a-kind [1 2 2 2 2]))))

(deftest test-four-of-a-kind
  (is (= {:four-of-a-kind 0} (four-of-a-kind [1 2 3 4 5])))
  (is (= {:four-of-a-kind 0} (four-of-a-kind [1 2 3 3 4])))
  (is (= {:four-of-a-kind 0} (four-of-a-kind [4 4 4 2 2])))
  (is (= {:four-of-a-kind 14} (four-of-a-kind [2 3 3 3 3])))
  (is (= {:four-of-a-kind 9} (four-of-a-kind [1 2 2 2 2]))))

(deftest test-full-house
  (is (= {:full-house 0} (full-house [1 2 3 4 5])))
  (is (= {:full-house 0} (full-house [1 1 2 2 3])))
  (is (= {:full-house 25} (full-house [2 2 2 3 3])))
  (is (= {:full-house 25} (full-house [3 4 3 4 3]))))

(deftest test-low-straight
  (is (= {:low-straight 0} (low-straight [1 1 1 1 1])))
  (is (= {:low-straight 0} (low-straight [1 2 3 5 5])))
  (is (= {:low-straight 30} (low-straight [1 2 3 4 2])))
  (is (= {:low-straight 30} (low-straight [1 2 3 4 6])))
  (is (= {:low-straight 30} (low-straight [1 2 3 4 5])))
  (is (= {:low-straight 30} (low-straight [2 5 3 4 3]))))

(deftest test-high-straight
  (is (= {:high-straight 0} (high-straight [1 1 1 1 1])))
  (is (= {:high-straight 0} (high-straight [1 2 3 5 5])))
  (is (= {:high-straight 40} (high-straight [1 2 3 4 5])))
  (is (= {:high-straight 40} (high-straight [2 5 3 4 1]))))

