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

trait RecursiveCancel[F[_]] extends RecursiveError[F] {

  def canceled: F[Unit]

  extension [A](fa: F[A]) {
    def onCancel(fin: F[Unit]): F[A]

    def forceR[B](f: F[B]): F[B]
  }

  def uncancelable[A](body: ([A] => F[A] => F[A]) => F[A]): F[A]

}

object RecursiveCancel extends RecursiveCancelCompanion

trait RecursiveCancelCompanion extends RecursiveErrorCompanion {

  inline def canceled[F[_]](using F: RecursiveCancel[F]): F[Unit] = F.canceled

  inline def uncancelable[F[_], A](body: ([A] => F[A] => F[A]) => F[A])(using F: RecursiveCancel[F]): F[A] =
    F.uncancelable(body)

}
