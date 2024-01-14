



final class ch08_18_work$_ {
def args = ch08_18_work_sc.args$
def scriptPath = """ch08_18_work.sc"""
/*<script>*/

// 8-18 ex 1
def check(time:MeetingTime, existingMeetings: List[MeetingTime]):Boolean =
  existingMeetings.forall( ng =>
    time.endHour <= ng.startHour || time.startHour >= ng.endHour )

//  case class MeetingTime(startHour: Int, endHour: Int)
// 既存 existing
def possibleMeetings(existingMeetings: List[MeetingTime],
                     startHour: Int, endHour: Int,
                     lengthHours: Int) :List[MeetingTime] =
  (startHour to endHour - lengthHours)
    .map(s => MeetingTime(s,s + lengthHours))
    .filter(time => check(time, existingMeetings))
    .toList

possibleMeetings(List(MeetingTime(11,13),MeetingTime(15,16)),10,17,2)

import cats.effect.IO
import cats.implicits._
import cats.effect.unsafe.implicits.global

def calendarEntries(name:String):IO[List[MeetingTime]] =
  import ch08_SchedulingMeetings.calendarEntriesApiCall
  IO.delay(calendarEntriesApiCall(name))

calendarEntries("taro")

def createMeeting(names: List[String], meetingTime: MeetingTime): IO[Unit] =
  import ch08_SchedulingMeetings.createMeetingApiCall
  IO.delay(createMeetingApiCall(names, meetingTime))

def scheduledMeetings(person1:String, person2:String): IO[List[MeetingTime]] =
  for {
    p1 <- calendarEntries(person1)
    p2 <- calendarEntries(person2)
  } yield p1 ++ p2

// 8-18 ex 2 P287
def schedule(person1: String, person2: String, lengthHours: Int): IO[Option[MeetingTime]]=
  scheduledMeetings(person1, person2)
    .map(meetings => possibleMeetings(meetings, 8, 16, lengthHours).headOption)

calendarEntries("Alice").orElse(calendarEntries("Alice")).orElse(IO.pure(List.empty))



/*</script>*/ /*<generated>*/
/*</generated>*/
}

object ch08_18_work_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new ch08_18_work$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export ch08_18_work_sc.script as ch08_18_work

