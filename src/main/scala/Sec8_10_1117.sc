import ch08_SchedulingMeetings.calendarEntriesApiCall
import cats.effect.IO
import cats.implicits.*
import cats.effect.unsafe.implicits.global

def calendarEntries(name:String): IO[List[MeetingTime]] =
  IO.delay(calendarEntriesApiCall(name))

calendarEntries("taro").unsafeRunSync()