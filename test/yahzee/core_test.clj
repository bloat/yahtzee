(ns yahzee.core-test
  (:use clojure.test
        yahzee.core))

(deftest test-sum-only-ones
  (is (= 5 (ones [1 1 1 1 1])))
  (is (= 3 (ones [1 1 1 3 3]))))

(deftest test-sum-only-twos
  (is (= 10 (twos [2 2 2 2 2])))
  (is (= 4 (twos [1 2 1 2 3]))))

(deftest test-sum-only-threes
  (is (= 15 (threes [3 3 3 3 3])))
  (is (= 0 (threes [1 2 4 5 6])))
  (is (= 6 (threes [1 2 3 3 6]))))

(deftest test-sum-only-fours
  (is (= 20 (fours [4 4 4 4 4])))
  (is (= 12 (fours [2 4 4 3 4]))))

(deftest test-sum-only-fours
  (is (= 20 (fours [4 4 4 4 4])))
  (is (= 12 (fours [2 4 4 3 4]))))

(deftest test-sum-only-fives
  (is (= 25 (fives [5 5 5 5 5])))
  (is (= 5 (fives [2 5 4 3 4]))))

(deftest test-sum-only-sixes
  (is (= 30 (sixes [6 6 6 6 6])))
  (is (= 18 (sixes [2 6 4 6 6]))))

(deftest test-sum-all-dice
  (is (= 15 (chance [1 2 3 5 4])))
  (is (= 6 (chance [1 1 1 1 2])))
  (is (= 28 (chance [6 6 6 5 5]))))

(deftest test-all-dice-equal
  (is (= 0 (yahzee [1 2 3 4 5])))
  (is (= 0 (yahzee [4 4 4 4 5])))
  (is (= 50 (yahzee [3 3 3 3 3])))
  (is (= 50 (yahzee [4 4 4 4 4]))))

(deftest test-three-of-a-kind
  (is (= 0 (three-of-a-kind [1 2 3 4 5])))
  (is (= 0 (three-of-a-kind [1 2 3 3 4])))
  (is (= 16 (three-of-a-kind [4 4 4 2 2])))
  (is (= 12 (three-of-a-kind [2 3 1 3 3])))
  (is (= 9 (three-of-a-kind [1 2 2 2 2]))))

(deftest test-four-of-a-kind
  (is (= 0 (four-of-a-kind [1 2 3 4 5])))
  (is (= 0 (four-of-a-kind [1 2 3 3 4])))
  (is (= 0 (four-of-a-kind [4 4 4 2 2])))
  (is (= 14 (four-of-a-kind [2 3 3 3 3])))
  (is (= 9 (four-of-a-kind [1 2 2 2 2]))))

(deftest test-full-house
  (is (= 0 (full-house [1 2 3 4 5])))
  (is (= 0 (full-house [1 1 2 2 3])))
  (is (= 25 (full-house [2 2 2 3 3])))
  (is (= 25 (full-house [3 4 3 4 3]))))

(deftest test-low-straight
  (is (= 0 (low-straight [1 1 1 1 1])))
  (is (= 0 (low-straight [1 2 3 5 5])))
  (is (= 30 (low-straight [1 2 3 4 2])))
  (is (= 30 (low-straight [1 2 3 4 5])))
  (is (= 30 (low-straight [2 5 3 4 3]))))

(deftest test-high-straight
  (is (= 0 (high-straight [1 1 1 1 1])))
  (is (= 0 (high-straight [1 2 3 5 5])))
  (is (= 40 (high-straight [1 2 3 4 5])))
  (is (= 40 (high-straight [2 5 3 4 1]))))

