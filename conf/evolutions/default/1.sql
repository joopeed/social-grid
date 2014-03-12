# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table dica (
  id                        bigint not null,
  autor_email               varchar(255),
  texto                     varchar(255),
  constraint pk_dica primary key (id))
;

create table dificuldade (
  id                        bigint not null,
  dificuldade               integer,
  constraint pk_dificuldade primary key (id))
;

create table disciplina (
  codigo                    bigint not null,
  nome                      varchar(255),
  creditos                  integer,
  constraint pk_disciplina primary key (codigo))
;

create table grade (
  id                        bigint not null,
  constraint pk_grade primary key (id))
;

create table periodo (
  id                        bigint not null,
  constraint pk_periodo primary key (id))
;

create table plano (
  id                        bigint not null,
  grade_id                  bigint,
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

create table disciplina_dificuldade (
  disciplina_codigo              bigint not null,
  dificuldade_id                 bigint not null,
  constraint pk_disciplina_dificuldade primary key (disciplina_codigo, dificuldade_id))
;

create table grade_disciplina (
  grade_id                       bigint not null,
  disciplina_codigo              bigint not null,
  constraint pk_grade_disciplina primary key (grade_id, disciplina_codigo))
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
create sequence dica_seq;

create sequence dificuldade_seq;

create sequence disciplina_seq;

create sequence grade_seq;

create sequence periodo_seq;

create sequence plano_seq;

create sequence usuario_seq;

alter table dica add constraint fk_dica_autor_1 foreign key (autor_email) references usuario (email) on delete restrict on update restrict;
create index ix_dica_autor_1 on dica (autor_email);
alter table plano add constraint fk_plano_grade_2 foreign key (grade_id) references grade (id) on delete restrict on update restrict;
create index ix_plano_grade_2 on plano (grade_id);
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

alter table disciplina_dificuldade add constraint fk_disciplina_dificuldade_dis_01 foreign key (disciplina_codigo) references disciplina (codigo) on delete restrict on update restrict;

alter table disciplina_dificuldade add constraint fk_disciplina_dificuldade_dif_02 foreign key (dificuldade_id) references dificuldade (id) on delete restrict on update restrict;

alter table grade_disciplina add constraint fk_grade_disciplina_grade_01 foreign key (grade_id) references grade (id) on delete restrict on update restrict;

alter table grade_disciplina add constraint fk_grade_disciplina_disciplin_02 foreign key (disciplina_codigo) references disciplina (codigo) on delete restrict on update restrict;

alter table periodo_disciplina add constraint fk_periodo_disciplina_periodo_01 foreign key (periodo_id) references periodo (id) on delete restrict on update restrict;

alter table periodo_disciplina add constraint fk_periodo_disciplina_discipl_02 foreign key (disciplina_codigo) references disciplina (codigo) on delete restrict on update restrict;

alter table plano_periodo add constraint fk_plano_periodo_plano_01 foreign key (plano_id) references plano (id) on delete restrict on update restrict;

alter table plano_periodo add constraint fk_plano_periodo_periodo_02 foreign key (periodo_id) references periodo (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists dica;

drop table if exists dica_usuario;

drop table if exists dificuldade;

drop table if exists disciplina;

drop table if exists disciplinas_dependentes;

drop table if exists disciplinas_requisitos;

drop table if exists disciplina_dica;

drop table if exists disciplina_dificuldade;

drop table if exists grade;

drop table if exists grade_disciplina;

drop table if exists periodo;

drop table if exists periodo_disciplina;

drop table if exists plano;

drop table if exists plano_periodo;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists dica_seq;

drop sequence if exists dificuldade_seq;

drop sequence if exists disciplina_seq;

drop sequence if exists grade_seq;

drop sequence if exists periodo_seq;

drop sequence if exists plano_seq;

drop sequence if exists usuario_seq;

