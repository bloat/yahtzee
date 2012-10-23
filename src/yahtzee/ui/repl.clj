(ns yahtzee.ui.repl
  (:use yahtzee.game))

(def this-game (atom (game)))

(defn new-game 
  "Start a new game. Deletes any data for any game currently in progress."
  [] 
  (reset! this-game (game)))


(defn roll
  "Rolls the dice. You can specify which dice to keep from the previous roll."
  [& to-keep]
  (cond 
   (not (every? #{0 1 2 3 4} to-keep)) "You can have five dice numbered 0, 1, 2, 3, 4"
   (= 14 (count (:card @this-game))) ["Game Over" "Final Score" (reduce + (vals (:card @this-game))) @this-game]
   :else (try
           (swap! this-game 
                  (fn [game]
                    (if (= (:rolls-left game) 3)
                      (first-roll game)
                      (apply next-roll game to-keep))))
           (catch Exception e (.getMessage e)))))

(defn score
  "Scores the current turn and starts a new one"
  [choice]
  (try
    (swap! this-game score-turn choice)
    (catch Exception e (.getMessage e))))
