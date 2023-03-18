import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.RequestContext
import kamon.Kamon
import kamon.trace.Span
import kamon.trace.Span.Key
import kamon.instrumentation.akka.http
import com.typesafe.scalalogging.Logger
import kamon.zipkin.ZipkinReporter
import kamon.context.Context
import scala.util.Try


object App {

  private val logger = Logger("App")
  val HOST = Try(sys.env("HOST")).getOrElse("localhost")
  val PORT = Try(sys.env("PORT").toInt).getOrElse(9003)

  implicit val system = ActorSystem("my-app")
  implicit val executionContext = system.dispatchers.lookup("custom-dispatcher")

  /**
   * Main Method
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    Kamon.init() // This must be first.

    // This Akka Http setup is not really important.
    Http().bindAndHandle(
      extractRequestContext { ctx =>
        // log all incoming http requests
        logHttpRequest(ctx)

        pathEndOrSingleSlash {
          complete("App is healthy.\n")
        } ~
          path("hello") {
            get {
              complete("Hello, World!")
            }
          } ~
          path("greet") {
            parameters('name) { name =>
              complete(s"Hello, $name!")
            }
          }
      },
      HOST, PORT)
      .map { _ =>
        logger.info(s"App is running at http://$HOST:$PORT/" + "  " + "http://localhost:5266/#/")
      } recover {
      case ex =>
        logger.error(s"Failed to bind to $HOST:$PORT.", ex.getMessage)

    }


    /**
     * Logs the incoming HTTP Request.
     *
     * We log this for demo purposes, to make it easier to see traces and
     * compare them with the log output.
     */



    def logHttpRequest(ctx: RequestContext): Unit = {
      val protocol = ctx.request.protocol.value
      val method = ctx.request.method.value
      val uri = ctx.request.uri.path.toString
      val span = Kamon.spanBuilder(s"$method $uri").start()
      span.tag("method", method)
      span.tag("uri", uri)
      val requestId = Kamon.currentSpan().trace.id.string
      logger.info(s"[$requestId] $protocol $method $uri")
      ctx.request.entity.dataBytes.runForeach(_ => ()).onComplete(_ => span.finish())(executionContext)

      // Store the current span in the current context
      Kamon.storeContext(Kamon.currentContext().withEntry(Key, span))
    }

  }}


      // Kamon is configured to use the X-Request-ID header as the trace id.
      // We grab it here so we can log it.
//
//
//def logHttpRequest(ctx: RequestContext)(implicit context: Context): Unit = {
//  val protocol = ctx.request.protocol.value
//  val method = ctx.request.method.value
//  val uri = ctx.request.uri.path.toString
//
//  // Start a new span for this HTTP request
//  val span = Kamon.spanBuilder(s"$method $uri")
//    .asChildOf(Kamon.currentSpan())
//    .tag("method", method)
//    .tag("uri", uri)
//    .start()
//
//  // Add a Zipkin annotation to mark the beginning of the span
//  Kamon.spanBuilder("http.request.received")
//    .asChildOf(span)
//    .tag("component", "akka-http")
//    .tag("http.method", method)
//    .tag("http.url", uri)
//    .start()
//    .finish()
//
//  // Log some information about the request
//  val requestId = span.trace.id.string
//  logger.info(s"[$requestId] $protocol $method $uri")
//
//  // Finish the span when the request is complete
//  ctx.request.entity.dataBytes.runForeach(_ => ()).onComplete(_ => {
//    // Add a Zipkin annotation to mark the end of the span
//    Kamon.spanBuilder("http.request.completed")
//      .asChildOf(span)
//      .tag("component", "akka-http")
//      .tag("http.method", method)
//      .tag("http.url", uri)
//      .start()
//      .finish()
//
//    // Finish the span
//    span.finish()
//  })(executionContext)
//
//  // Store the current span in the current context
//  Kamon.storeContext(context.withEntry(Span.Key, span))
//}


/* check the kamon.init() if works "http://localhost:5266/#/" */
//
//




/*
Here are the key steps that are happening in this code:

Extract some information about the incoming HTTP request (method, URI, etc.).
Create a new Kamon span for the incoming HTTP request.
Add some tags to the span to provide more context.
Check if there is a parent span in the current context.
If there is a parent span, create a child span and use that to log information about the incoming HTTP request.
If there is no parent span, use the existing span to log information about the incoming HTTP request.
End the appropriate span once the HTTP request has been processed.
Store the current span in the current context.
Overall, this code appears to be using Kamon to help with tracing and context propagation in an HTTP server.
 By creating a new span for each incoming request and storing it in the current context,
you can track information about the processing of that request through different parts of your codebase.
 Additionally, by creating child spans when appropriate (i.e., when there is a parent span in the current context),
 you can more finely track the processing of specific parts of the request handling code.
 */