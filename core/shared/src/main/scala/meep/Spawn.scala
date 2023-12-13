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

trait Spawn[F[_]] extends RecursiveCancel[F] {

  def cede: F[Unit]

  def never[A]: F[A]

  extension [A](fa: F[A]) {
    def start: F[Fiber[F, A]]

    def racePair[B](fb: F[B]): F[
      Either[
        (Outcome[A], Fiber[F, B]),
        (Fiber[F, A], Outcome[B])
      ]
    ]
  }

}

object Spawn extends SpawnCompanion

trait SpawnCompanion extends RecursiveCancelCompanion {

  inline def cede[F[_]](using F: Spawn[F]): F[Unit] = F.cede

  inline def never[F[_], A](using F: Spawn[F]): F[A] = F.never

}
