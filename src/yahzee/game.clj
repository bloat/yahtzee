(ns yahzee.game)

(def empty-card 
  {:ones 0 :twos 0 :threes 0 :fours 0 :fives 0 :sixes 0
   :bonus 0 :three-of-a-kind 0 :four-of-a-kind 0
   :full-house 0 :low-straight 0 :high-straight 0
   :yahzee 0 :chance 0 :yahzee-bonus 0})

(defn score-roll [score card]
  (into card score))

