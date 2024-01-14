import ch08_CardGame.castTheDie
import ch08_CardGame.drawAPointCard

import cats.effect.IO
import cats.implicits._
import cats.effect.unsafe.implicits.global


IO.delay(castTheDie()).orElse(IO.pure(0))
IO.delay(drawAPointCard()).orElse(IO.delay(castTheDie()))
IO.delay(castTheDie()).orElse(IO.delay(castTheDie())).orElse(IO.pure(0))
IO.delay(castTheDie()).orElse(IO.pure(0)).flatMap(n => IO.delay(drawAPointCard()).orElse(IO.pure(0)).map(m => n + m))

(for {
  n <- IO.delay(drawAPointCard())
  m <- IO.delay(castTheDie())
  o <- IO.delay(castTheDie())
} yield  n + m + o
  ).orElse(IO.pure(0))

