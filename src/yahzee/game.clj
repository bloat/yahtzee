(ns yahzee.game
  (use yahzee.score))

(def empty-card 
  {})

(defn score-roll [roll card choice]
  (let [scored-card ((score-fns choice) roll card)]
    (if (= scored-card card)
      (throw (RuntimeException. "You can't use that score there!"))
      scored-card)))

