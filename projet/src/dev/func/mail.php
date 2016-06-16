<?php
/**
 * Fonction pour envoyer un mail au format MarkDown => format HTML facilement
 * @author Antoine du HAMEL
 * @project intraJE
 */
function email ($dest, $sujet, $message_md, $values=array(), $fromAddr=null, $fromName=null, $replyAddr=null, $replyName=null, array $attachment=array())
{
    if(!CONFIG\MAIL\AVAILABLE) return;

    $mail = new PHPMailer;

    //Tell PHPMailer to use SMTP
    $mail->isSMTP();
    //Enable SMTP debugging
    // 0 = off (for production use)
    // 1 = client messages
    // 2 = client and server messages
    $mail->SMTPDebug = PROD_ENVIRONEMENT ? 0 : 2;

    //Set the hostname of the mail server
    $mail->Host = CONFIG\MAIL\SMTP_SERVER;
    $mail->Port = CONFIG\MAIL\SMTP_PORT;
    $mail->SMTPSecure = CONFIG\MAIL\SMTP_SECURE;
    $mail->SMTPAuth = CONFIG\MAIL\SMTP_AUTH;
    $mail->Username = CONFIG\MAIL\SMTP_DEFAULT_USER_NAME;
    $mail->Password = CONFIG\MAIL\SMTP_DEFAULT_PASSWORD;

    $mail->setFrom($fromAddr ?? CONFIG\MAIL\DEFAULT_FROM_ADDRESS, $fromName ?? CONFIG\MAIL\DEFAULT_FROM_NAME);
    $mail->addReplyTo($replyAddr ?? CONFIG\MAIL\DEFAULT_REPLY_ADDRESS, $replyName ?? CONFIG\MAIL\DEFAULT_REPLY_NAME);

    array_map([$mail, 'addAddress'], (array)$dest);
    $mail->Subject = $sujet;

    $md = new Parsedown;
    try
    {
        list($message_md, $message_html) = $md->file($message_md, $values);
    }
    catch (Exception $e)
    {
        $message_html = $md->text($message_md);
    }

    $mail->msgHTML(email_html($message_html));
    $mail->AltBody = $message_md."\n\nSEIO via intraJE\n\n".'*Ce mail a été envoyé automatiquement par intraJE, merci de ne pas y répondre.*';

    array_map([$mail, 'addAttachment'], $attachment);

    return $mail->send();
}

function email_preview($dest, $sujet, $message_md, $values=array())
{
    $md = new Parsedown;
    try
    {
        list($message_md, $message_html) = $md->file($message_md, $values);
    }
    catch (Exception $e)
    {
        $message_html = $md->text($message_md);
    }

    $doc = new HTML\Doc('email_preview');
    $doc->body.= HTML::h1('email_preview : Prévisualisation d\'email');
    $doc->body.= HTML::header(HTML::strong('Destinataire : ').$dest.'<br>'.HTML::strong('Objet : ').$sujet);
    $doc->body.= HTML::container('warning', HTML::em(['style'=>'padding:2em .5em;display:block;'], 'Attention, le rendu sera potentiellement différent pour chaque client mail !'));

    $doc->body.=HTML::h3('Message HTML :').HTML::iframe(['srcdoc'=>email_html($message_html), 'style'=>'width:100%;height:29em;'], '');
    $doc->body.=HTML::hr();
    $doc->body.=HTML::h3('Message texte :').HTML::pre(['style'=>'border:2px inset;padding:1em;word-wrap:break-word;'], $message_md."\n\nSEIO via intraJE\n\n".'*Ce mail a été envoyé automatiquement par intraJE, merci de ne pas y répondre.*');

    exit;
}

function email_html($html)
{
    $dir = 'http://'.$_SERVER['HTTP_HOST'].ROOT_DIR.'/images/mail/';
    $utf8Style = 'display:block;max-width:16px;font-size:16px;';
    return '<html><head><meta charset="utf-8"/></head><body>'.
                HTML::section(['id'=>'message', 'class'=>'message'], $html).

                HTML::section(['id'=>'signature', 'class'=>'signature'],
                    HTML::hr().
                    new HTML\Table(['tbody'=>[[
                        HTML::container('logo', HTML::img(['src'=>$dir.'logo_sign.png', 'alt'=>'Logo SEIO'])),
                        new HTML\Table([
                            ['style'=>'padding-top:.4em;color:#757575;'],
                            'caption'=>'L\'equipe du SEIO, Junior Entreprise de l\'ESEO',
                            'tbody'=>[
                                [
                                    HTML::span(['style'=>$utf8Style], '&#9993;'), HTML::a('mailto:contact@seio.org', 'contact@seio.org'),
                                    HTML::span(['style'=>$utf8Style], '&#9742;'), '09.52.83.76.98'
                                ],
                                [
                                    HTML::span(['style'=>$utf8Style], '&#127757;'), HTML::a('http://www.seio.org/', 'seio.org'),
                                    HTML::img(['src'=>$dir.'linkedin_grey.png', 'alt'=>'Logo LinkedIn', 'style'=>'height:16px;width:16px;']), HTML::a('https://www.linkedin.com/company/823600', 'LinkedIn')
                                ],
                                [
                                    HTML::img(['src'=>$dir.'facebook_grey.png', 'alt'=>'Logo Facebook', 'style'=>'height:16px;width:16px;']), HTML::a('https://www.facebook.com/juniorentreprise.seio', 'Page Facebook'),
                                    HTML::img(['src'=>$dir.'twitter_grey.png', 'alt'=>'Logo Twitter', 'style'=>'height:16px;width:16px;']), HTML::a('https://twitter.com/seio_je', 'Fil Twitter')
                                ],
                            ],
                        ])
                    ]]])).
                HTML::section(['id'=>'warning'],
                    HTML::p(['style'=>'clear:both;padding:.6em;'], HTML::em('Ce message vous a été envoyé automatiquement par intraJE, merci de ne pas y répondre.'))).

                HTML::style(file_get_contents(PROJECT_ROOT.'/images/mail/style.css')).
        '</body></html>';
}
