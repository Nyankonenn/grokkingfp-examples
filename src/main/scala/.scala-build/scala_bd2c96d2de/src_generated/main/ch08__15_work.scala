



final class ch08__15_work$_ {
def args = ch08__15_work_sc.args$
def scriptPath = """ch08__15_work.sc"""
/*<script>*/
import cats.effect.IO
import cats.implicits._
import cats.effect.unsafe.implicits.global

// work1
def calendarEntries(name:String):IO[List[MeetingTime]] =
  import ch08_SchedulingMeetings.calendarEntriesApiCall
  IO.delay(calendarEntriesApiCall(name))

calendarEntries("taro")

// work2
def createMeeting(names: List[String], meetingTime: MeetingTime): IO[Unit] =
  import ch08_SchedulingMeetings.createMeetingApiCall
  IO.delay(createMeetingApiCall(names, meetingTime))

// work3
def scheduledMeetings(person1:String, person2:String): IO[List[MeetingTime]] =
  for {
    p1 <- calendarEntries(person1)
    p2 <- calendarEntries(person2)
  } yield p1 ++ p2

val dogsMeetings = scheduledMeetings("taro","jiro")
dogsMeetings.unsafeRunSync()

/*</script>*/ /*<generated>*/
/*</generated>*/
}

object ch08__15_work_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new ch08__15_work$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export ch08__15_work_sc.script as ch08__15_work

