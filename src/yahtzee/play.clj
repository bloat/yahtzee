(ns yahzee.ui.repl
  (:use yahzee.game))

(def this-game (atom (game)))

(defn new-game 
  "Start a new game. Deletes any data for any game currently in progress."
  [] 
  (reset! this-game (game)))


(defn roll
  "Rolls the dice. You can specify which dice to keep from the previous roll."
  [& to-keep]
  (if (every? #{0 1 2 3 4} to-keep)
    (try
      (swap! this-game 
             (fn [game]
               (if (= (:rolls-left game) 3)
                 (first-roll game)
                 (apply next-roll game to-keep))))
      (catch Exception e (.getMessage e)))
    "You can have five dice numbered 0, 1, 2, 3, 4"))

(defn score
  "Scores the current turn and starts a new one"
  [choice]
  (try
    (swap! this-game score-turn choice)
    (catch Exception e (.getMessage e))))
