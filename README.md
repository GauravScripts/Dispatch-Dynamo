# Dispatch-Dynamo
If you encounter the following error while running ng serve:
Error: node_modules/@tensorflow/tfjs-core/dist/hash_util.d.ts:4:49 - error TS2304: Cannot find name 'Long'.

4 export declare function hexToLong(hex: string): Long;
~~~~


Error: node_modules/@tensorflow/tfjs-core/dist/hash_util.d.ts:5:69 - error TS2304: Cannot find name 'Long'.

5 export declare function fingerPrint64(s: Uint8Array, len?: number): Long;
~~~~
## Add import * as Long from "long"; in hash_util.d.ts file before ng serve
https://s10.gifyu.com/images/homedf4c4bab03fd9fc4.gif
<hr>


https://s10.gifyu.com/images/Video_230314132832.gif
<hr>

https://s2.gifyu.com/images/Video_230314133930.gif

<hr>

https://s10.gifyu.com/images/Video_230314134518.gif
<hr>

https://s2.gifyu.com/images/Video_230314134831Segment1.gif
<hr>

https://s2.gifyu.com/images/Video_230314134831Segment1-2.gif


