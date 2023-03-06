import { Component } from '@angular/core';

@Component({
  selector: 'app-chat-bot',
  templateUrl: './chat-bot.component.html',
  styleUrls: ['./chat-bot.component.css']
})
export class ChatBotComponent {
  ngOnInit() {
    (function(d, m){
      var kommunicateSettings =
          {"appId":"1957a36812b65d3e891fad78b01aa67b9","popupWidget":true,"automaticChatOpenOnNavigation":true};
      var s = document.createElement("script"); s.type = "text/javascript"; s.async = true;
      s.src = "https://widget.kommunicate.io/v2/kommunicate.app";
      var h = document.getElementsByTagName("head")[0]; h.appendChild(s);

      (window as any).kommunicate = m; m._globals = kommunicateSettings;
    })

    (document, (window as any).kommunicate || {});
  }
}
