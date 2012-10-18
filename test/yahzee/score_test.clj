(ns yahzee.score-test
  (:use clojure.test
        yahzee.score))

(deftest test-sum-only-ones
  (is (= {:ones 5} (ones [1 1 1 1 1] {})))
  (is (= {:ones 3} (ones [1 1 1 3 3] {})))
  (is (= {:ones 3} (ones [1 1 1 1 1] {:ones 3})))
  (is (= {:ones 1 :twos 2 :threes 3 :fours 4 :fives 5 :sixes 6 :bonus 0} 
         (ones [1 2 2 2 2] {:twos 2 :threes 3 :fours 4 :fives 5 :sixes 6})))
  (is (= {:ones 3 :twos 6 :threes 9 :fours 12 :fives 15 :sixes 18 :bonus 35} 
         (ones [1 1 1 2 2] {:twos 6 :threes 9 :fours 12 :fives 15 :sixes 18})))
  (is (= {:yahzee 50 :ones 5 :yahzee-bonus 100}
         (ones [1 1 1 1 1] {:yahzee 50}))))

(deftest test-sum-only-twos
  (is (= {:twos 10} (twos [2 2 2 2 2] {})))
  (is (= {:twos 4} (twos [1 2 1 2 3] {})))
  (is (= {:twos 4} (twos [1 2 1 2 2] {:twos 4})))
  (is (= {:ones 1 :twos 8 :threes 3 :fours 4 :fives 5 :sixes 6 :bonus 0} 
         (twos [1 2 2 2 2] {:ones 1 :threes 3 :fours 4 :fives 5 :sixes 6})))
  (is (= {:ones 3 :twos 6 :threes 9 :fours 12 :fives 15 :sixes 18 :bonus 35} 
         (twos [1 1 2 2 2] {:ones 3 :threes 9 :fours 12 :fives 15 :sixes 18})))
  (is (= {:twos 4 :yahzee 50} 
         (twos [2 2 2 2 3] {:yahzee 50 :twos 4}))))

(deftest test-sum-only-threes
  (is (= {:threes 15} (threes [3 3 3 3 3] {})))
  (is (= {:threes 0} (threes [1 2 4 5 6] {})))
  (is (= {:threes 6} (threes [1 2 3 3 6] {})))
  (is (= {:threes 6} (threes [1 3 3 3 6] {:threes 6})))
  (is (= {:ones 1 :twos 2 :threes 0 :fours 4 :fives 5 :sixes 6 :bonus 0} 
         (threes [1 2 2 2 2] {:ones 1 :twos 2 :fours 4 :fives 5 :sixes 6})))
  (is (= {:ones 3 :twos 6 :threes 9 :fours 12 :fives 15 :sixes 18 :bonus 35} 
         (threes [1 3 3 2 3] {:ones 3 :twos 6 :fours 12 :fives 15 :sixes 18})))
  (is (= {:threes 6 :yahzee 50}
         (threes [3 3 3 3 3] {:yahzee 50 :threes 6}))))

(deftest test-sum-only-fours
  (is (= {:fours 20} (fours [4 4 4 4 4] {})))
  (is (= {:fours 12} (fours [2 4 4 3 4] {})))
  (is (= {:fours 12} (fours [2 4 4 4 4] {:fours 12})))
  (is (= {:ones 1 :twos 2 :threes 3 :fours 4 :fives 5 :sixes 6 :bonus 0} 
         (fours [1 2 4 2 2] {:ones 1 :twos 2 :threes 3 :fives 5 :sixes 6})))
  (is (= {:ones 3 :twos 6 :threes 9 :fours 12 :fives 15 :sixes 18 :bonus 35} 
         (fours [1 4 4 4 2] {:ones 3 :twos 6 :threes 9 :fives 15 :sixes 18}))))

(deftest test-sum-only-fives
  (is (= {:fives 25} (fives [5 5 5 5 5] {})))
  (is (= {:fives 5} (fives [2 5 4 3 4] {})))
  (is (= {:fives 5} (fives [2 5 5 3 4] {:fives 5})))
  (is (= {:ones 1 :twos 2 :threes 3 :fours 4 :fives 5 :sixes 6 :bonus 0} 
         (fives [1 2 2 5 2] {:ones 1 :twos 2 :threes 3 :fours 4 :sixes 6})))
  (is (= {:ones 3 :twos 6 :threes 9 :fours 12 :fives 15 :sixes 18 :bonus 35} 
         (fives [5 5 1 5 2] {:ones 3 :twos 6 :threes 9 :fours 12 :sixes 18}))))

(deftest test-sum-only-sixes
  (is (= {:sixes 30} (sixes [6 6 6 6 6] {})))
  (is (= {:sixes 18} (sixes [2 6 4 6 6] {})))
  (is (= {:sixes 18} (sixes [2 4 4 6 6] {:sixes 18})))
  (is (= {:ones 1 :twos 2 :threes 3 :fours 4 :fives 5 :sixes 6 :bonus 0} 
         (sixes [1 2 6 2 2] {:ones 1 :twos 2 :threes 3 :fours 4 :fives 5})))
  (is (= {:ones 3 :twos 6 :threes 9 :fours 12 :fives 15 :sixes 18 :bonus 35} 
         (sixes [1 1 6 6 6] {:ones 3 :twos 6 :threes 9 :fours 12 :fives 15}))))

(deftest test-sum-all-dice
  (is (= {:chance 15} (chance [1 2 3 5 4] {})))
  (is (= {:chance 6} (chance [1 1 1 1 2] {})))
  (is (= {:chance 28} (chance [6 6 6 5 5] {})))
  (is (= {:chance 28} (chance [6 6 3 3 5] {:chance 28}))))

(deftest test-all-dice-equal
  (is (= {:yahzee 0} (yahzee [1 2 3 4 5] {})))
  (is (= {:yahzee 0} (yahzee [4 4 4 4 5] {})))
  (is (= {:yahzee 50} (yahzee [3 3 3 3 3] {})))
  (is (= {:yahzee 50} (yahzee [4 4 4 4 4] {})))
  (is (= {:yahzee 50} (yahzee [4 4 4 3 4] {:yahzee 50}))))

(deftest test-three-of-a-kind
  (is (= {:three-of-a-kind 0} (three-of-a-kind [1 2 3 4 5] {})))
  (is (= {:three-of-a-kind 0} (three-of-a-kind [1 2 3 3 4] {})))
  (is (= {:three-of-a-kind 16} (three-of-a-kind [4 4 4 2 2] {})))
  (is (= {:three-of-a-kind 12} (three-of-a-kind [2 3 1 3 3] {})))
  (is (= {:three-of-a-kind 9} (three-of-a-kind [1 2 2 2 2] {})))
  (is (= {:three-of-a-kind 9} (three-of-a-kind [1 2 6 2 2] {:three-of-a-kind 9}))))

(deftest test-four-of-a-kind
  (is (= {:four-of-a-kind 0} (four-of-a-kind [1 2 3 4 5] {})))
  (is (= {:four-of-a-kind 0} (four-of-a-kind [1 2 3 3 4] {})))
  (is (= {:four-of-a-kind 0} (four-of-a-kind [4 4 4 2 2] {})))
  (is (= {:four-of-a-kind 14} (four-of-a-kind [2 3 3 3 3] {})))
  (is (= {:four-of-a-kind 9} (four-of-a-kind [1 2 2 2 2] {})))
  (is (= {:four-of-a-kind 9} (four-of-a-kind [6 2 2 2 2] {:four-of-a-kind 9}))))

(deftest test-full-house
  (is (= {:full-house 0} (full-house [1 2 3 4 5] {})))
  (is (= {:full-house 0} (full-house [1 1 2 2 3] {})))
  (is (= {:full-house 25} (full-house [2 2 2 3 3] {})))
  (is (= {:full-house 25} (full-house [3 4 3 4 3] {})))
  (is (= {:full-house 25} (full-house [3 4 3 2 3] {:full-house 25}))))

(deftest test-low-straight
  (is (= {:low-straight 0} (low-straight [1 1 1 1 1] {})))
  (is (= {:low-straight 0} (low-straight [1 2 3 5 5] {})))
  (is (= {:low-straight 30} (low-straight [1 2 3 4 2] {})))
  (is (= {:low-straight 30} (low-straight [1 2 3 4 6] {})))
  (is (= {:low-straight 30} (low-straight [1 2 3 4 5] {})))
  (is (= {:low-straight 30} (low-straight [2 5 3 4 3] {})))
  (is (= {:low-straight 30} (low-straight [2 6 3 4 3] {:low-straight 30}))))

(deftest test-high-straight
  (is (= {:high-straight 0} (high-straight [1 1 1 1 1] {})))
  (is (= {:high-straight 0} (high-straight [1 2 3 5 5] {})))
  (is (= {:high-straight 40} (high-straight [1 2 3 4 5] {})))
  (is (= {:high-straight 40} (high-straight [2 5 3 4 1] {})))
  (is (= {:high-straight 40} (high-straight [2 6 3 4 1] {:high-straight 40}))))

