/*
 * Copyright 2013 Commonwealth Computer Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package geomesa.core

import org.apache.accumulo.core.data.{Key, Value}
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.TaskInputOutputContext
import org.geotools.data.FeatureWriter
import org.opengis.feature.simple.{SimpleFeatureType, SimpleFeature}

package object data {
  import geomesa.core.index._
  import collection.JavaConversions._

  val INSTANCE_ID        = "geomesa.instance.id"
  val ZOOKEEPERS         = "geomesa.zookeepers"
  val ACCUMULO_USER      = "geomesa.user"
  val ACCUMULO_PASS      = "geomesa.pass"
  val AUTHS              = "geomesa.auths"
  val TABLE              = "geomesa.table"
  val FEATURE_NAME       = "geomesa.feature.name"
  val ATTRIBUTES_CF      = new Text("attributes")
  val BOUNDS_CF          = new Text("bounds")
  val SCHEMA_CF          = new Text("schema")
  val DTGFIELD_CF        = new Text("dtgfield")
  val METADATA_TAG       = "~METADATA"
  val METADATA_TAG_END   = s"$METADATA_TAG~~"
  val EMPTY_STRING       = ""
  val EMPTY_VALUE        = new Value(Array[Byte]())
  val EMPTY_COLQ         = new Text(EMPTY_STRING)
  val WHOLE_WORLD_BOUNDS = "-180.0:180.0:-90.0:90.0"

  type TASKIOCTX = TaskInputOutputContext[_, _, Key, Value]
  type SFFeatureWriter = FeatureWriter[SimpleFeatureType, SimpleFeature]

  def extractDtgField(sft: SimpleFeatureType) =
    sft.getAttributeDescriptors
      .filter { _.getUserData.contains(SF_PROPERTY_START_TIME) }
      .headOption
      .map { _.getName.toString }
      .getOrElse(SF_PROPERTY_START_TIME)

}
