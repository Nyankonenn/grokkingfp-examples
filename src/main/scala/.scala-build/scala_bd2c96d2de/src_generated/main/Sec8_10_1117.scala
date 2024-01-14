



final class Sec8_10_1117$_ {
def args = Sec8_10_1117_sc.args$
def scriptPath = """Sec8_10_1117.sc"""
/*<script>*/
import ch08_SchedulingMeetings.calendarEntriesApiCall
import cats.effect.IO
import cats.implicits.*
import cats.effect.unsafe.implicits.global

def calendarEntries(name:String): IO[List[MeetingTime]] =
  IO.delay(calendarEntriesApiCall(name))

calendarEntries("taro").unsafeRunSync()
/*</script>*/ /*<generated>*/
/*</generated>*/
}

object Sec8_10_1117_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new Sec8_10_1117$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export Sec8_10_1117_sc.script as Sec8_10_1117

