package objektwerks

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

object Serializer:
  given JsonValueCodec[Page] = JsonCodecMaker.make[Page]( CodecMakerConfig.withDiscriminatorFieldName(None) )

  given JsonValueCodec[Task] = JsonCodecMaker.make[Task]( CodecMakerConfig.withDiscriminatorFieldName(None) )