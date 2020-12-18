package org.usecase.event

import org.usecase.user.context.RequestContext

abstract class Event(val context: RequestContext)

class FooEvent(context: RequestContext, val id: Long) : Event(context)
