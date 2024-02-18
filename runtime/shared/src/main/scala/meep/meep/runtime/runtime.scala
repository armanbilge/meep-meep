package meep.meep.runtime

abstract class Coro {
  
  def run(runtime: Runtime, state: Int): Int

  def runStolen(runtime: Runtime, state: Int): Int

}

abstract class Runtime private[runtime] {

  def schedule(coro: Coro, state: Int): Unit

  def sleep(nanos: Long, coro: Coro, state: Int): CancelToken

  def cancelSleep(token: CancelToken): Unit

  def poller(): AnyRef

  def pleaseYield(): Boolean

  def prepareForBlocking(): Boolean

  def startCompute(): Unit

  def endCompute(): Unit

}

abstract class CancelToken private[runtime] ()
