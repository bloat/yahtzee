(ns yahtzee.score)

(def top-section [:ones :twos :threes :fours :fives :sixes])

(defn yahtzee? [dice]
  (apply = dice))

(defn yahtzee-bonus [card dice] 
  (if (and (:yahtzee card) 
           (yahtzee? dice))
    (update-in card [:yahtzee-bonus] (fn [yb] (if (nil? yb) 100 (+ 100 yb))))
    card))

(defn if-not-scored [kw card score]
  (if (kw card)
    card
    (assoc card kw score)))

(defn not-scored? [kw card]
  (not (kw card)))

(defn must-score-top?
  "Second and subsequent yahtzees must go in the top section if possible."
  [dice card]
  (and (yahtzee? dice)
       (not-scored? ([nil :ones :twos :threes :fours :fives :sixes] (first dice)) card)))

(defn bonus [card dice]
  (yahtzee-bonus 
   (if (every? #(contains? card %) top-section)
     (if-not-scored :bonus card (if (< 62 (reduce + (map card top-section))) 35 0))
     card)
   dice))

(defn count-dice [dice n]
  "Count the number of dice showing n"
  (count (filter (partial = n) dice)))

(defn sum-numbers
  "Sum only the given number, unless that number has already been scored."
  [kw n dice card]
  (if (not-scored? kw card)
    (bonus
     (assoc card kw (* n (count-dice dice n)))
     dice)
    card))

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
  (if (must-score-top? dice card)
    card
    (if-not-scored :chance card (apply + dice))))

(defn yahtzee
  "Five of a kind"
  [dice card]
  (if-not-scored :yahtzee card (if (yahtzee? dice) 50 0)))

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
  (if (must-score-top? dice card)
    card
    (if-not-scored :three-of-a-kind card (n-of-a-kind dice 3))))

(defn four-of-a-kind
  "Sum all dice provided there are three of one number"
  [dice card]
  (if (must-score-top? dice card)
    card
    (if-not-scored :four-of-a-kind card (n-of-a-kind dice 4))))

(defn full-house
 "Three of one and two of another"
 [dice card]
 (if (must-score-top? dice card)
   card
   (if-not-scored :full-house 
                  card
                  (if (or (yahtzee? dice) 
                          (= #{2 3} (set (value-counts dice))))
                    25 
                    0))))

(defn low-straight
  "Four consecutive numbers"
  [dice card]
  (if (must-score-top? dice card)
    card
    (let [sorted (sort (set dice))]
      (if-not-scored :low-straight  
                     card
                     (if (or (= sorted [1 2 3 4])
                             (= sorted [2 3 4 5])
                             (= sorted [3 4 5 6])
                             (= sorted [1 2 3 4 5])
                             (= sorted [2 3 4 5 6])
                             (= sorted [1 3 4 5 6])
                             (= sorted [1 2 3 4 6])
                             (yahtzee? dice))
                       30
                       0)))))

(defn high-straight
  "Five consecutive numbers"
  [dice card]
  (let [sorted (sort (set dice))]
    (if (must-score-top? dice card)
      card
      (if-not-scored :high-straight 
                     card
                     (if (or (= sorted [1 2 3 4 5])
                             (= sorted [2 3 4 5 6])
                             (yahtzee? dice))
                       40
                       0)))))

(def score-fns 
  {:ones ones :twos twos :threes threes :fours fours :fives fives :sixes sixes
   :bonus bonus :three-of-a-kind three-of-a-kind :four-of-a-kind four-of-a-kind
   :full-house full-house :low-straight low-straight :high-straight high-straight 
   :yahtzee yahtzee :chance chance :yahtzee-bonus yahtzee-bonus})

(defn all-scores [dice card]
  (into {} (map (fn [[key s-fn]] [key (s-fn dice card)]) score-fns)))
