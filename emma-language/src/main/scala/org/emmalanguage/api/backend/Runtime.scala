/*
 * Copyright © 2014 TU Berlin (emma@dima.tu-berlin.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.emmalanguage
package api.backend

import api.DataBag
import api.Meta

/** Runtime operators (backend-agnostic IR nodes API). */
trait Runtime[E] {

  /** Mark the dataset for caching. */
  def cache[A: Meta](xs: DataBag[A])(implicit env: E): DataBag[A]

  /** Fuse a groupBy and a subsequent fold into a single operator. */
  def foldGroup[A: Meta, B: Meta, K: Meta](
    xs: DataBag[A], key: A => K, sng: A => B, uni: (B, B) => B
  )(implicit env: E): DataBag[(K, B)]
}