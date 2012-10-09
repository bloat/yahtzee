(ns yahzee.core)

(defn count-dice [dice n]
  "Count the number of dice showing n"
  (count (filter (partial = n) dice)))

(defn ones 
  "Sum only ones"
  [dice]
  (count-dice dice 1))

(defn twos 
  "Sum only twos"
  [dice]
  (* 2 (count-dice dice 2)))

(defn threes
  "Sum only threes"
  [dice]
  (* 3 (count-dice dice 3)))

(defn fours
  "Sum only fours"
  [dice]
  (* 4 (count-dice dice 4)))

(defn fives
  "Sum only fives"
  [dice]
  (* 5 (count-dice dice 5)))

(defn sixes
  "Sum only sixes"
  [dice]
  (* 6 (count-dice dice 6)))

(defn chance
  "Sum all dice"
  [dice]
  (apply + dice))

(defn yahzee
  "Five of a kind"
  [dice]
  (if (apply = dice) 50 0))

(defn value-counts [dice]
  "Returns counts of the number of times each number appears"
  (map (fn [[_ c]] (count c)) (group-by identity dice)))

(defn n-of-a-kind
  "Sum all dice provided there are n of one number"
  [dice n]
  (if (< (dec n) (apply max (value-counts dice)))
    (apply + dice)
    0))

(defn three-of-a-kind
  "Sum all dice provided there are three of one number"
  [dice]
  (n-of-a-kind dice 3))

(defn four-of-a-kind
  "Sum all dice provided there are three of one number"
  [dice]
  (n-of-a-kind dice 4))

(defn full-house
 "Three of one and two of another"
 [dice]
 (if (= #{2 3} (set (value-counts dice)))
   25 
   0))

(defn low-straight
  "Four consecutive numbers"
  [dice]
  (let [sorted (sort (set dice))]
    (if (or (= sorted [1 2 3 4])
            (= sorted [2 3 4 5])
            (= sorted [3 4 5 6])
            (= sorted [1 2 3 4 5])
            (= sorted [2 3 4 5 6]))
      30
      0)))

(defn high-straight
  "Five consecutive numbers"
  [dice]
  (let [sorted (sort (set dice))]
    (if (or (= sorted [1 2 3 4 5])
            (= sorted [2 3 4 5 6]))
      40
      0)))
