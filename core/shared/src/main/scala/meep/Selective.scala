/*
 * Copyright 2023 Arman Bilge
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

package meep

trait Selective[F[_]] extends Applicative[F] {

  extension [A, B](fab: F[Either[A, B]]) {
    def branch[C](f: F[A => C], g: F[B => C]): F[C]
  }

  extension [A, B](fab: F[A => B]) {
    def ap(fa: F[A]): F[B] = fa.map(Right[A, A](_)).branch(fab, fab)
  }

  extension [A](fa: F[A]) {
    def tailRec[B](ff: F[A => Either[A, B]]): F[B]
  }

}

object Selective extends SelectiveCompanion

trait SelectiveCompanion extends ApplicativeCompanion
