(ns yahzee.game
  (use [yahzee score roll]))

(def empty-card 
  {})

(defn score-roll [card roll choice]
  (let [scored-card ((score-fns choice) roll card)]
    (if (= scored-card card)
      (throw (RuntimeException. "You can't use that score there!"))
      scored-card)))

(defn game []
  {:card empty-card :dice nil :rolls-left 3})

(defn first-roll [game]
  (if (= (:rolls-left game) 3)
    {:card (:card game) :dice (five-dice) :rolls-left 2}
    (throw (RuntimeException. "Not the first roll of a turn."))))

(defn next-roll [game & to-keep]
  (if (or (= (:rolls-left game) 2)
          (= (:rolls-left game) 1))
    (-> game
        (update-in [:rolls-left] dec)
        (update-in [:dice] #(apply keep-and-roll % to-keep)))
    (throw (RuntimeException. "Not the second or third roll of a turn."))))

(defn score-turn [game choice]
  (if (= 3 (:rolls-left game))
    (throw (RuntimeException. "You should roll first!"))
    {:card (score-roll (:card game) (:dice game) choice)
     :dice nil
     :rolls-left 3}))

