



final class ch08_CastingDie_work$_ {
def args = ch08_CastingDie_work_sc.args$
def scriptPath = """ch08_CastingDie_work.sc"""
/*<script>*/
import ch08_CastingDie.WithFailures.castTheDieImpure
import cats.effect.IO
import cats.implicits._
import cats.effect.unsafe.implicits.global

def castTheDie(): IO[Int] = IO.delay(castTheDieImpure())
castTheDie()

//castTheDieImpure() + castTheDieImpure()

val result = castTheDie().flatMap( n => castTheDie().map(m=>n+m))
result // 何をしていようと、ここではIO[Int]でありその前提でコンパイルされる


/*</script>*/ /*<generated>*/
/*</generated>*/
}

object ch08_CastingDie_work_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new ch08_CastingDie_work$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export ch08_CastingDie_work_sc.script as ch08_CastingDie_work

