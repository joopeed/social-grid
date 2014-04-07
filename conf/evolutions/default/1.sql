# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table avaliacao_de_usuario (
  id                        bigint not null,
  usuario_email             varchar(255),
  dificuldade               integer,
  constraint pk_avaliacao_de_usuario primary key (id))
;

create table dica (
  id                        bigint not null,
  autor_email               varchar(255),
  texto                     varchar(255),
  constraint pk_dica primary key (id))
;

create table disciplina (
  codigo                    bigint not null,
  nome                      varchar(255),
  creditos                  integer,
  constraint pk_disciplina primary key (codigo))
;

create table periodo (
  id                        bigint not null,
  constraint pk_periodo primary key (id))
;

create table plano (
  id                        bigint not null,
  qnt_periodos              integer,
  idx_periodo_atual         integer,
  constraint pk_plano primary key (id))
;

create table usuario (
  email                     varchar(255) not null,
  nome                      varchar(255),
  senha                     varchar(255),
  plano_id                  bigint,
  constraint pk_usuario primary key (email))
;


create table dica_usuario (
  dica_id                        bigint not null,
  usuario_email                  varchar(255) not null,
  constraint pk_dica_usuario primary key (dica_id, usuario_email))
;

create table disciplinas_dependentes (
  disciplina_codigo              bigint not null,
  dependente_id                  bigint not null,
  constraint pk_disciplinas_dependentes primary key (disciplina_codigo, dependente_id))
;

create table disciplinas_requisitos (
  disciplina_codigo              bigint not null,
  requisito_codigo               bigint not null,
  constraint pk_disciplinas_requisitos primary key (disciplina_codigo, requisito_codigo))
;

create table disciplina_dica (
  disciplina_codigo              bigint not null,
  dica_id                        bigint not null,
  constraint pk_disciplina_dica primary key (disciplina_codigo, dica_id))
;

create table disciplina_avaliacao_de_usuario (
  disciplina_codigo              bigint not null,
  avaliacao_de_usuario_id        bigint not null,
  constraint pk_disciplina_avaliacao_de_usuario primary key (disciplina_codigo, avaliacao_de_usuario_id))
;

create table periodo_disciplina (
  periodo_id                     bigint not null,
  disciplina_codigo              bigint not null,
  constraint pk_periodo_disciplina primary key (periodo_id, disciplina_codigo))
;

create table plano_periodo (
  plano_id                       bigint not null,
  periodo_id                     bigint not null,
  constraint pk_plano_periodo primary key (plano_id, periodo_id))
;
create sequence avaliacao_de_usuario_seq;

create sequence dica_seq;

create sequence disciplina_seq;

create sequence periodo_seq;

create sequence plano_seq;

create sequence usuario_seq;

alter table avaliacao_de_usuario add constraint fk_avaliacao_de_usuario_usuari_1 foreign key (usuario_email) references usuario (email) on delete restrict on update restrict;
create index ix_avaliacao_de_usuario_usuari_1 on avaliacao_de_usuario (usuario_email);
alter table dica add constraint fk_dica_autor_2 foreign key (autor_email) references usuario (email) on delete restrict on update restrict;
create index ix_dica_autor_2 on dica (autor_email);
alter table usuario add constraint fk_usuario_plano_3 foreign key (plano_id) references plano (id) on delete restrict on update restrict;
create index ix_usuario_plano_3 on usuario (plano_id);



alter table dica_usuario add constraint fk_dica_usuario_dica_01 foreign key (dica_id) references dica (id) on delete restrict on update restrict;

alter table dica_usuario add constraint fk_dica_usuario_usuario_02 foreign key (usuario_email) references usuario (email) on delete restrict on update restrict;

alter table disciplinas_dependentes add constraint fk_disciplinas_dependentes_di_01 foreign key (disciplina_codigo) references disciplina (codigo) on delete restrict on update restrict;

alter table disciplinas_dependentes add constraint fk_disciplinas_dependentes_di_02 foreign key (dependente_id) references disciplina (codigo) on delete restrict on update restrict;

alter table disciplinas_requisitos add constraint fk_disciplinas_requisitos_dis_01 foreign key (disciplina_codigo) references disciplina (codigo) on delete restrict on update restrict;

alter table disciplinas_requisitos add constraint fk_disciplinas_requisitos_dis_02 foreign key (requisito_codigo) references disciplina (codigo) on delete restrict on update restrict;

alter table disciplina_dica add constraint fk_disciplina_dica_disciplina_01 foreign key (disciplina_codigo) references disciplina (codigo) on delete restrict on update restrict;

alter table disciplina_dica add constraint fk_disciplina_dica_dica_02 foreign key (dica_id) references dica (id) on delete restrict on update restrict;

alter table disciplina_avaliacao_de_usuario add constraint fk_disciplina_avaliacao_de_us_01 foreign key (disciplina_codigo) references disciplina (codigo) on delete restrict on update restrict;

alter table disciplina_avaliacao_de_usuario add constraint fk_disciplina_avaliacao_de_us_02 foreign key (avaliacao_de_usuario_id) references avaliacao_de_usuario (id) on delete restrict on update restrict;

alter table periodo_disciplina add constraint fk_periodo_disciplina_periodo_01 foreign key (periodo_id) references periodo (id) on delete restrict on update restrict;

alter table periodo_disciplina add constraint fk_periodo_disciplina_discipl_02 foreign key (disciplina_codigo) references disciplina (codigo) on delete restrict on update restrict;

alter table plano_periodo add constraint fk_plano_periodo_plano_01 foreign key (plano_id) references plano (id) on delete restrict on update restrict;

alter table plano_periodo add constraint fk_plano_periodo_periodo_02 foreign key (periodo_id) references periodo (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists avaliacao_de_usuario;

drop table if exists dica;

drop table if exists dica_usuario;

drop table if exists disciplina;

drop table if exists disciplinas_dependentes;

drop table if exists disciplinas_requisitos;

drop table if exists disciplina_dica;

drop table if exists disciplina_avaliacao_de_usuario;

drop table if exists periodo;

drop table if exists periodo_disciplina;

drop table if exists plano;

drop table if exists plano_periodo;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists avaliacao_de_usuario_seq;

drop sequence if exists dica_seq;

drop sequence if exists disciplina_seq;

drop sequence if exists periodo_seq;

drop sequence if exists plano_seq;

drop sequence if exists usuario_seq;

