
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

def schedulingProgram(getName: IO[String], showMeeting: Option[MeetingTime] => IO[Unit]):IO[Unit] =
  for {
    name1  <- getName
    name2  <- getName
    result <- schedule(name1, name2, 2)
    _      <- showMeeting(result)
  } yield ()

import ch08_SchedulingMeetings.consolePrint
import ch08_SchedulingMeetings.consoleGet

schedulingProgram(IO.delay("tora"), (meet) => IO.delay(println(meet)))


