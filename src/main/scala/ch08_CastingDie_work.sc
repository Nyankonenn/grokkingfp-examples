import ch08_CastingDie.WithFailures.castTheDieImpure
import cats.effect.IO
import cats.implicits._
import cats.effect.unsafe.implicits.global

def castTheDie(): IO[Int] = IO.delay(castTheDieImpure())
castTheDie()

//castTheDieImpure() + castTheDieImpure()

val result = castTheDie().flatMap( n => castTheDie().map(m=>n+m))
result // 何をしていようと、ここではIO[Int]でありその前提でコンパイルされる

