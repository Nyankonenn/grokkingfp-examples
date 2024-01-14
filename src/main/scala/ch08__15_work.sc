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
