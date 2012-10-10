(ns yahzee.roll)

(defn roll []
  (+ 1 (rand-int 6)))

(defn five-dice []
  (repeatedly 5 roll))

(defn keep-and-roll [dice & indices]
  (concat (map (vec dice) indices) (repeatedly (- 5 (count indices)) roll)))