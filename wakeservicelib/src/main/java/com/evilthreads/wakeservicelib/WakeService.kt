/*Copyright 2020 Chris Basinger

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/
package com.evilthreads.wakeservicelib

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import com.evilthreads.wakescopelib.wakeScope

/*
            (   (                ) (             (     (
            )\ ))\ )    *   ) ( /( )\ )     (    )\ )  )\ )
 (   (   ( (()/(()/(  ` )  /( )\()|()/((    )\  (()/( (()/(
 )\  )\  )\ /(_))(_))  ( )(_)|(_)\ /(_))\((((_)( /(_)) /(_))
((_)((_)((_|_))(_))   (_(_()) _((_|_))((_))\ _ )(_))_ (_))
| __\ \ / /|_ _| |    |_   _|| || | _ \ __(_)_\(_)   \/ __|
| _| \ V /  | || |__    | |  | __ |   / _| / _ \ | |) \__ \
|___| \_/  |___|____|   |_|  |_||_|_|_\___/_/ \_\|___/|___/
....................../´¯/)
....................,/¯../
.................../..../
............./´¯/'...'/´¯¯`·¸
........../'/.../..../......./¨¯\
........('(...´...´.... ¯~/'...')
.........\.................'...../
..........''...\.......... _.·´
............\..............(
..............\.............\...
*/
/*implement WakeService and override Intent.doWork()
* your args will be in the receiver of type Intent*/
abstract class WakeService(name: String, val wakeTimeout: Long = -1): IntentService(name){
    private lateinit var wakeLock: PowerManager.WakeLock

    /*override this method and do your work*/
    abstract fun Intent.doWork()

    /*do not override this*/
    override fun onHandleIntent(intent: Intent?) {
        if(wakeTimeout == -1L)
            wakeScope {
                intent?.doWork()
            }
        else
            wakeScope(wakeTimeout) {
                intent?.doWork()
            }
    }
}