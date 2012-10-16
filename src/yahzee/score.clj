(ns yahzee.score)

(def top-section [:ones :twos :threes :fours :fives :sixes])

(defn bonus [card]
  (if (every? #(contains? card %) top-section)
    (if-not-scored :bonus card (if (< 62 (reduce + (map card top-section))) 35 0))
    card))

(defn count-dice [dice n]
  "Count the number of dice showing n"
  (count (filter (partial = n) dice)))

(defn if-not-scored [kw card score]
  (if (kw card)
    card
    (assoc card kw score)))

(defn sum-numbers
  "Sum only the given number, unless that number has already been scored."
  [kw n dice card]
  (bonus
   (if-not-scored kw card (* n (count-dice dice n)))))

(defn ones 
  "Sum only ones"
  [dice card]
  (sum-numbers :ones 1 dice card))

(defn twos 
  "Sum only twos"
  [dice card]
  (sum-numbers :twos 2 dice card))

(defn threes
  "Sum only threes"
  [dice card]
  (sum-numbers :threes 3 dice card))

(defn fours
  "Sum only fours"
  [dice card]
  (sum-numbers :fours 4 dice card))

(defn fives
  "Sum only fives"
  [dice card]
  (sum-numbers :fives 5 dice card))

(defn sixes
  "Sum only sixes"
  [dice card]
  (sum-numbers :sixes 6 dice card))

(defn chance
  "Sum all dice"
  [dice card]
  (if-not-scored :chance card (apply + dice)))

(defn yahzee
  "Five of a kind"
  [dice card]
  (if-not-scored :yahzee card (if (apply = dice) 50 0)))

(defn value-counts
  "Returns counts of the number of times each number appears"
  [dice]
  (map (fn [[_ c]] (count c)) (group-by identity dice)))

(defn n-of-a-kind
  "Sum all dice provided there are n of one number"
  [dice n]
  (if (< (dec n) (apply max (value-counts dice)))
    (apply + dice)
    0))

(defn three-of-a-kind
  "Sum all dice provided there are three of one number"
  [dice card]
  (if-not-scored :three-of-a-kind card (n-of-a-kind dice 3)))

(defn four-of-a-kind
  "Sum all dice provided there are three of one number"
  [dice card]
  (if-not-scored :four-of-a-kind card (n-of-a-kind dice 4)))

(defn full-house
 "Three of one and two of another"
 [dice card]
 (if-not-scored :full-house 
                card
                (if (= #{2 3} (set (value-counts dice)))
                  25 
                  0)))

(defn low-straight
  "Four consecutive numbers"
  [dice card]
  (let [sorted (sort (set dice))]
    (if-not-scored :low-straight  
                   card
                   (if (or (= sorted [1 2 3 4])
                           (= sorted [2 3 4 5])
                           (= sorted [3 4 5 6])
                           (= sorted [1 2 3 4 5])
                           (= sorted [2 3 4 5 6])
                           (= sorted [1 3 4 5 6])
                           (= sorted [1 2 3 4 6]))
                     30
                     0))))

(defn high-straight
  "Five consecutive numbers"
  [dice card]
  (let [sorted (sort (set dice))]
    (if-not-scored :high-straight 
                   card
                   (if (or (= sorted [1 2 3 4 5])
                           (= sorted [2 3 4 5 6]))
                     40
                     0))))

(defn yahzee-bonus [dice card])

(def score-fns 
  {:ones ones :twos twos :threes threes :fours fours :fives fives :sixes sixes
   :bonus bonus :three-of-a-kind three-of-a-kind :four-of-a-kind four-of-a-kind
   :full-house full-house :low-straight low-straight :high-straight high-straight 
   :yahzee yahzee :chance chance :yahzee-bonus yahzee-bonus})

(defn all-scores [dice card]
  (into {} (map (fn [[key s-fn]] [key (s-fn dice card)]) score-fns)))
